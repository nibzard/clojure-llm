(defn multiply
  "Complete the function that takes two integers and returns 
  the product of their unit digits.
  Assume the input is always valid."
  [a b]
  (* (Math/abs (rem a 10))
     (Math/abs (rem b 10))))