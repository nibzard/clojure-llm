(defn loss_amount
  "	Write a function that gives loss amount on a sale if the given amount has loss else return 0."
  [actual_cost sale_amount]
  (if (> actual_cost sale_amount)
    (- actual_cost sale_amount)
    0))