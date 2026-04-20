(defn is_product_even
  "	Write a function to check whether the product of numbers in a list is even or not."
  [arr]
  (if (some even? arr) "Even" "Odd"))