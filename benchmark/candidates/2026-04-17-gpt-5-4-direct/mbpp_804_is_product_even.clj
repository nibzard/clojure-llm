(defn is_product_even
  "	Write a function to check whether the product of numbers in a list is even or not."
  [arr]
  (boolean
   (and (seq arr)
        (some even? arr))))