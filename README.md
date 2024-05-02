## Follow up questions 

1. **How long did you spend on the test? What would you add if you had more time?** 
  R: Unfortunately, I couldn't implement unit tests, but while conducting integration tests with Wiremock, I spent half a day modifying the returned data to observe how my solution behaves when applying the rules. If I had more time, I could implement a specific module to apply business rules and also an in-memory database like H2DB to store the data provided by Wiremock or use it as a cache to reduce requests to the service and response time. This could certainly make the requests faster.

2. **What was the most useful feature that was added to the latest version of your chosen language? Please include a snippet of code that shows how you've used it.** 
  R: I used _Pattern Matching for switch_ to make the extensions of the Promotion class cleaner and more readable as shown in the code below:

  ```java
  public Promotion createPromotion(List<Promotion> promotions) {
  
          return switch (promotions.get(0).getType()) {
              case BUY_X_GET_Y_FREE -> new BuyXGetYFreePromotion(promotions.get(0));
              case FLAT_PERCENT -> new FlatPercentPromotion(promotions.get(0));
              case QTY_BASED_PRICE_OVERRIDE -> new QuantityBasedPriceOverridePromotion(promotions.get(0));
              default -> throw new IllegalStateException("Promotion not supported: " + promotions.get(0).getType());
          };
      }
  ```

  

3. **What did you find most difficult?** 
  R: I didn't encounter major complexities in developing the solution, just some minor obstacles in serializing JSON with different quantities and types of attributes returned in the list of promotions for each product.

4. What mechanism did you put in place to track down issues in production on this code? If you didn’t put anything, write down what you could do. 
  R: I used a method in an abstract class so that I could effectively apply the Open/Close principle, but it could also be developed using an interface.

5. The Wiremock represents one source of information. We should be prepared to integrate with more sources. List the steps that we would need to take to add more sources of items with diferent formats and promotions.
  R:
- Add the new types of attributes to the abstract Promotion class;
- Then extend the Promotion class with the class that will represent the new promotion;
- Implement the applyPromotion() method according to the rules of the new promotion;
- Add the created class to the PromotionFactory class that will provide an instance dynamically according to the response.