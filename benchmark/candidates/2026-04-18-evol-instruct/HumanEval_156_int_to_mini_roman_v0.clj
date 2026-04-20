(defn int_to_mini_roman
  "Given a positive integer, return its Roman numeral equivalent as a lowercase string.
  This variation supports values from 1 to 3999.
  Examples:
  >>> (int_to_mini_roman 4)
  \"iv\"
  >>> (int_to_mini_roman 944)
  \"cmxliv\"
  >>> (int_to_mini_roman 3999)
  \"mmmcmxcix\""
  [number]
  (let [pairs [[1000 "m"] [900 "cm"] [500 "d"] [400 "cd"]
               [100 "c"] [90 "xc"] [50 "l"] [40 "xl"]
               [10 "x"] [9 "ix"] [5 "v"] [4 "iv"] [1 "i"]]]
    (loop [n number, acc ""]
      (if (zero? n)
        acc
        (let [[value numeral] (first (filter #(<= (first %) n) pairs))]
          (recur (- n value) (str acc numeral)))))))