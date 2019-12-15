package com.kai.newsletter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kai.justapp.R;

public class kopi extends AppCompatActivity {

    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kopi);
    }
    public void increment(View view){
        if (quantity == 100) {
            Toast.makeText(this, "Maksimal pemesanan adalah 100 cangkir", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view){
        if (quantity == 1){
            Toast.makeText(this, "Minimum pemesanan adalah 1 cangkir", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    public void submitOrder(View view){
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();


        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateTopping = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolateTopping = chocolateTopping.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolateTopping);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolateTopping);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Pesanan Kopi atas nama " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) !=null) {
            startActivity(intent);
        }
        //displayMessage(priceMessage);
    }
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;

        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }
        if (addChocolate) {
            basePrice = basePrice + 2;
        }
        return quantity * basePrice;
    }

    private String createOrderSummary (String name, int price, boolean addWhippedCream, boolean addChocolateTopping) {
        String priceMessage = "Name = " + name;
        priceMessage +="\nWhipped Cream =  " + addWhippedCream;
        priceMessage +="\nChocolate Topping = " + addChocolateTopping;
        priceMessage += "\nQuantity =  "+ quantity;
        priceMessage += "\nTotal $"+ price;
        return priceMessage;
    }
//    public void submitOrder(View view){
//        int price = quantity * 5;
//        String priceMessage = "Harganya $" + price ;
//        displayMessage(priceMessage);
//    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void kopi(View view) {
        Intent intent = new Intent(kopi.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * This method displays the given price on the screen.
     */
//    private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }
//    private void displayMessage(String message) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(message);
//    }

}
