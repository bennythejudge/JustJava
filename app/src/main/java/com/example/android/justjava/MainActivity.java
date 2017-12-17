/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 *
 */
package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int unit_price = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    private int calculatePrice(int quantity, int unit_price, boolean withWhippedCream,
                               boolean withChcolate) {
        int tot = 0;
        tot = tot + ((withChcolate) ? 1 : 0);
        tot = tot + ((withWhippedCream) ? 1 : 0);

        return (quantity * unit_price) + tot;
    }

    private String createOrderSummary(int unit_price, int quantity, boolean hasWhippedCream,
                                      boolean hasChocolate, String customerName) {

        String whippedcream =  (hasWhippedCream) ? "With Whipped Cream on Top\n" : "";
        String chocolate =  (hasChocolate) ? "With Chocolate\n" : "";

        return customerName + "\n" +
                whippedcream +
                chocolate +
                "Quantity: " + quantity + "\n" +
                "Total: " + calculatePrice(quantity, unit_price, hasWhippedCream, hasChocolate) +
                "\n" +
                "Thank you!";
    }


    public void submitOrder(View view) {

        boolean hasWhippedCream = ((CheckBox) findViewById(R.id.whipped_cream)).isChecked();
        boolean hasChocolate = ((CheckBox) findViewById(R.id.chocolate)).isChecked();
        String customerName = ((EditText) findViewById(R.id.customer_name)).getText().toString();

        Log.d("submitOrder", "whipped cream: " + hasWhippedCream + " chocolate: " +
                hasChocolate);
        Log.d("submitOrder", "customer name: " + customerName);

        String priceMessage = createOrderSummary(unit_price, quantity, hasWhippedCream,
                hasChocolate, customerName );

        displayMessage(priceMessage);
    }

    public void increment(View view) {
        if (quantity<100) {
            quantity++;
        } else {
            Toast.makeText(this, "Dude, too much coffee will drive you crazy!",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity > 0) {
            quantity--;
        } else {
            Toast.makeText(this, "Dude, order something!", Toast.LENGTH_SHORT).show();
            return;
        }
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the given price on the screen.
     * Udacity wants me to delete this because they don't update the price in real time
     * as the quantity changes
     */
//    private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }
}