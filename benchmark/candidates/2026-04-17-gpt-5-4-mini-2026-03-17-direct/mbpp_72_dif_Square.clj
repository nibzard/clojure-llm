(defn dif_Square
  "	Write a cljthon function to check whether the given number can be represented as the difference of two squares or not."
  [n]
  (boolean
   (and (integer? n)
        (not (neg? n))
        (or (zero? n)
            (some #(zero? (mod n %))
                  (range 2 (inc (long (Math/sqrt n)))))))))