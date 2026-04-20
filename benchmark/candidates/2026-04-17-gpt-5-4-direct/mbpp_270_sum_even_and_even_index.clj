(defn sum_even_and_even_index
  "	Write a cljthon function to find the sum of even numbers at even positions of a list."
  [arr]
  (reduce
   +
   0
   (for [[idx x] (map-indexed vector (or arr []))
         :when (and (even? idx) (number? x) (even? x))]
     x)))