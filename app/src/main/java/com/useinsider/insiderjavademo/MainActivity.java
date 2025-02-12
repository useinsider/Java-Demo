package com.useinsider.insiderjavademo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.useinsider.insider.ContentOptimizerDataType;
import com.useinsider.insider.Insider;
import com.useinsider.insider.InsiderEvent;
import com.useinsider.insider.InsiderGender;
import com.useinsider.insider.InsiderIdentifiers;
import com.useinsider.insider.InsiderProduct;
import com.useinsider.insider.InsiderUser;
import com.useinsider.insider.MessageCenterData;
import com.useinsider.insider.RecommendationEngine;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;


public class MainActivity extends AppCompatActivity {

    private InsiderUser currentUser;
    private InsiderIdentifiers identifiers;
    private InsiderProduct insiderExampleProduct;
    private String[] taxonomy;
    private String[] arr;
    private String[] productIDs;


    private Button userAttributesButton;
    private Button loginButton;
    private Button logoutButton;
    private Button reinitPartnerNameButton;
    private Button triggerEventButton;
    private Button createProductButton;
    private Button enableInappButton;
    private Button disableInappButton;
    private Button itemAddedButton;
    private Button itemRemovedButton;
    private Button itemPurchaseButton;
    private Button cartClearButton;
    private Button itemAddedWishlistButton;
    private Button itemRemovedWishlistButton;
    private Button wishlistClearButton;
    private Button smartRecommenderButton;
    private Button socialProofButton;
    private Button homePageVisitButton;
    private Button productPageVisitButton;
    private Button cartPageVisitButton;
    private Button categoryPageVisitButton;
    private Button gdprTrueButton;
    private Button gdprFalseButton;
    private Button messageCenterButton;
    private Button contentOptimizerButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentUser = Insider.Instance.getCurrentUser();

        taxonomy = new String[]{"taxonomy1", "taxonomy2", "taxonomy3"};
        arr = new String[]{"value1", "value2", "value3"};
        productIDs = new String[]{"productID1", "productID2", "productID3"};

        userAttributesButton = findViewById(R.id.userAttributesButton);
        loginButton = findViewById(R.id.loginButton);
        logoutButton = findViewById(R.id.logoutButton);
        reinitPartnerNameButton = findViewById(R.id.reinitPartnerNameButton);
        triggerEventButton = findViewById(R.id.triggerEventButton);
        createProductButton = findViewById(R.id.createProductButton);
        enableInappButton = findViewById(R.id.enableInappButton);
        disableInappButton = findViewById(R.id.disableInappButton);
        itemAddedButton = findViewById(R.id.itemAddedButton);
        itemRemovedButton = findViewById(R.id.itemRemovedButton);
        itemPurchaseButton = findViewById(R.id.itemPurchaseButton);
        cartClearButton = findViewById(R.id.cartClearButton);
        itemAddedWishlistButton = findViewById(R.id.itemAddedWishlistButton);
        itemRemovedWishlistButton = findViewById(R.id.itemRemovedWishlistButton);
        wishlistClearButton = findViewById(R.id.wishlistClearButton);
        smartRecommenderButton = findViewById(R.id.smartRecommenderButton);
        socialProofButton = findViewById(R.id.socialProofButton);
        homePageVisitButton = findViewById(R.id.homePageVisitButton);
        productPageVisitButton = findViewById(R.id.productPageVisitButton);
        cartPageVisitButton = findViewById(R.id.cartPageVisitButton);
        categoryPageVisitButton = findViewById(R.id.categoryPageVisitButton);
        gdprTrueButton = findViewById(R.id.gdprTrueButton);
        gdprFalseButton = findViewById(R.id.gdprFalseButton);
        messageCenterButton = findViewById(R.id.messageCenterButton);
        contentOptimizerButton = findViewById(R.id.contentOptimizerButton);


        userAttributesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Setting User Attributes in chainable way.
                currentUser.setName("Insider")
                        .setSurname("Demo")
                        .setAge(23)
                        .setGender(InsiderGender.OTHER)
                        .setBirthday((new Date()))
                        .setEmailOptin(true)
                        .setSMSOptin(false)
                        .setPushOptin(true)
                        .setLocationOptin(true)
                        .setFacebookID("Facebook-ID")
                        .setTwitterID("Twitter-ID")
                        .setLanguage("TR")
                        .setLocale("tr_TR")
                        .setWhatsappOptin(true);

                // Setting User Identifiers.
                identifiers = new InsiderIdentifiers();
                identifiers.addEmail("mobile@useinsider.com")
                        .addPhoneNumber("+901234567")
                        .addUserID("CRM-ID");

                // Setting custom attributes.
                // MARK: Your attribute key should be all lowercase and should not include any
                // special or non Latin characters or any space, otherwise this attribute will be
                // ignored. You can use underscore _.
                currentUser.setCustomAttributeWithString("string_attribute",
                        "This is Insider.");
                currentUser.setCustomAttributeWithInt("int_attribute", 10);
                currentUser.setCustomAttributeWithDouble("double_attribute", 20.5);
                currentUser.setCustomAttributeWithBoolean("bool_attribute", true);
                currentUser.setCustomAttributeWithDate("date_attribute", new Date());

                // MARK: You can only call the method with array of string otherwise this event
                // will be ignored.
                Insider.Instance.getCurrentUser().setCustomAttributeWithArray("key", arr);
                Insider.Instance.getCurrentUser().unsetCustomAttribute("key");

                Insider.Instance.signUpConfirmation();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.login(identifiers);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.logout();
            }
        });

        reinitPartnerNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MARK: You can reinitialize Insider SDK with a different partner name.
                Insider.Instance.reinitWithPartnerName("new_partner_name");
            }
        });

        triggerEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // --- EVENT --- //

                // You can create an event without parameters and call the build method
                Insider.Instance.tagEvent("first_event").build();

                // You can create an event then add parameters and call the build method
                Insider.Instance.tagEvent("second_event").addParameterWithInt("int_parameter",
                        10).build();

                // You can create an object and add the parameters later
                InsiderEvent insiderExampleEvent = Insider.Instance.tagEvent("third_event");

                insiderExampleEvent.addParameterWithString("string_parameter",
                                "This is Insider.")
                        .addParameterWithInt("int_parameter", 10)
                        .addParameterWithDouble("double_parameter", 10.5)
                        .addParameterWithBoolean("bool_parameter", true)
                        .addParameterWithDate("date_parameter", new Date());

                // MARK: You can only call the method with array of string otherwise this event will
                // be ignored.
                insiderExampleEvent.addParameterWithArray("array_parameter", arr);

                // Adding multiple parameters using Map
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("string_param", "insider");
                parameters.put("int_param", 42);
                parameters.put("double_param", 3.14);
                parameters.put("bool_param", true);
                parameters.put("date_param", new Date());
                parameters.put("array_param", arr);

                insiderExampleEvent.addParameters(parameters).build();

                // Do not forget to call build method once you are done with parameters.
                insiderExampleEvent.build();
            }
        });

        createProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // --- PRODUCT --- //

                // MARK: If any parameter which is passed to this method is nil / null or an empty
                // string, it will return an empty and invalid Insider Product Object. Note that an
                // invalid Insider Product object will be ignored for any product related operations.
                // You can crete Insider Product and add attributes later on it.

                insiderExampleProduct = Insider.Instance.createNewProduct("productID",
                        "productName", taxonomy, "imageURL", 1000.5, "currency");

                // Setting Product Attributes in chainable way.
                insiderExampleProduct.setColor("color")
                        .setVoucherName("voucherName")
                        .setVoucherDiscount(10.5)
                        .setPromotionName("promotionName")
                        .setPromotionDiscount(10.5)
                        .setSize("size")
                        .setSalePrice(10.5)
                        .setShippingCost(10.5)
                        .setQuantity(10)
                        .setStock(10)
                        .setInStock(true);

                // Setting custom attributes.
                insiderExampleProduct.setCustomAttributeWithString("string_parameter",
                                "This is Insider.")
                        .setCustomAttributeWithInt("int_parameter", 10)
                        .setCustomAttributeWithDouble("double_parameter", 10.5)
                        .setCustomAttributeWithBoolean("bool_parameter", true)
                        .setCustomAttributeWithDate("date_parameter", new Date())
                        .setCustomAttributeWithArray("array_parameter", arr);

                //Setting the groupcode of your Insider product object.
                insiderExampleProduct.setGroupCode("XXYYZZ");

            }
        });

        enableInappButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insider.Instance.enableInAppMessages();
            }
        });

        disableInappButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insider.Instance.disableInAppMessages();
            }
        });

        itemAddedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (insiderExampleProduct != null) {
                    Insider.Instance.itemAddedToCart(insiderExampleProduct);
                }
            }
        });

        itemRemovedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insider.Instance.itemRemovedFromCart("productID");
            }
        });

        itemPurchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insider.Instance.itemPurchased("uniqueSaleID", insiderExampleProduct);
            }
        });

        cartClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insider.Instance.cartCleared();
            }
        });

        itemAddedWishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (insiderExampleProduct != null) {
                    Insider.Instance.itemAddedToWishlist(insiderExampleProduct);
                }
            }
        });

        itemRemovedWishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insider.Instance.itemRemovedFromWishlist("productID");
            }
        });

        wishlistClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insider.Instance.wishlistCleared();
            }
        });


        smartRecommenderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // --- RECOMMENDATION ENGINE --- //

                // ID comes from your smart recommendation campaign.
                // Please follow the language code structure. For instance tr_TR.
                Insider.Instance.getSmartRecommendation(1, "tr_TR", "TRY",
                        new RecommendationEngine.SmartRecommendation() {
                            @Override
                            public void loadRecommendationData(JSONObject recommendation) {
                                // Handle here
                                Timber.tag("INSIDER").d("getSmartRecommendation: %s"
                                        , recommendation.toString());
                            }
                        });

                Insider.Instance.getSmartRecommendationWithProduct(insiderExampleProduct,
                        1, "tr_TR", new RecommendationEngine.SmartRecommendation() {
                            @Override
                            public void loadRecommendationData(JSONObject recommendation) {
                                // Handle here
                                Timber.tag("INSIDER")
                                        .d("getSmartRecommendationWithProduct: %s",
                                                recommendation.toString());
                            }
                        });

                Insider.Instance.getSmartRecommendationWithProductIDs(productIDs, 3, "en_US", "USD", new RecommendationEngine.SmartRecommendation() {
                    @Override
                    public void loadRecommendationData(JSONObject recommendation) {
                        // Handle here
                        Timber.tag("INSIDER")
                                .d("getSmartRecommendationWithProductIDs: %s",
                                        recommendation.toString());
                    }
                });

            }
        });

        socialProofButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insider.Instance.visitProductDetailPage(insiderExampleProduct);
            }
        });

        homePageVisitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // --- PAGE VISITING --- //
                Insider.Instance.visitHomePage();

            }
        });

        productPageVisitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insider.Instance.visitProductDetailPage(insiderExampleProduct);
            }
        });

        cartPageVisitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsiderProduct[] insiderExampleProducts = {insiderExampleProduct,
                        insiderExampleProduct};
                Insider.Instance.visitCartPage(insiderExampleProducts);
            }
        });

        categoryPageVisitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insider.Instance.visitListingPage(taxonomy);
            }
        });

        gdprTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // --- GDPR --- //

                // MARK: Please note that by default our SDK is collecting the data so you don't
                // have to call this function if you are not asking users consents.
                // MARK: If you set false, the user will not share any data or receive any push
                // until you set back true.
                Insider.Instance.setGDPRConsent(true);
            }
        });

        gdprFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MARK: If you set false, the user will not share any data or receive any push
                // until you set back true.
                Insider.Instance.setGDPRConsent(false);
            }
        });

        messageCenterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // --- MESSAGE CENTER --- //

                Insider.Instance.getMessageCenterData(20, new Date(1546300800), new Date(),
                        new MessageCenterData() {
                            @Override
                            public void loadMessageCenterData(JSONArray messageCenterData) {
                                // Handle here
                                Timber.tag("INSIDER").d("getMessageCenterData: %s",
                                        messageCenterData.toString());
                            }
                        });
            }
        });

        contentOptimizerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String
                String contentOptimizerString = Insider.Instance
                        .getContentStringWithName("string_variable_name",
                                "defaultValue", ContentOptimizerDataType.ELEMENT);

                Timber.tag("INSIDER").d("getContentStringWithName: %s",
                        contentOptimizerString);

                // Boolean
                boolean contentOptimizerBool = Insider.Instance
                        .getContentBoolWithName("bool_variable_name", true,
                                ContentOptimizerDataType.ELEMENT);

                Timber.tag("INSIDER").d("getContentBoolWithName: %s",
                        contentOptimizerBool);

                // Integer
                int contentOptimizerInt = Insider.Instance
                        .getContentIntWithName("int_variable_name", 10,
                                ContentOptimizerDataType.ELEMENT);

                Timber.tag("INSIDER").d("getContentIntWithName: %s", contentOptimizerInt);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
} 