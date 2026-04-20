(defn mul_even_odd
  "Write a function to find the product of first even and odd number of a given list."
  [list1]
  (let [even-num (some #(when (even? %) %) list1)
        odd-num (some #(when (odd? %) %) list1)]
    (when (and even-num odd-num)
      (* even-num odd-num))))