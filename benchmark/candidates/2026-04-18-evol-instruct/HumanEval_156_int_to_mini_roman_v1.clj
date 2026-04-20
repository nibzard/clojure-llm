(defn int_to_mini_roman
  "Convert a positive integer from 1 to 1000 into its lowercase Roman numeral representation.

  Examples:
  (int_to_mini_roman 4)   ;=> \"iv\"
  (int_to_mini_roman 19)  ;=> \"xix\"
  (int_to_mini_roman 944) ;=> \"cmxliv\""
  [number]
  (let [roman [[1000 "m"]
               [900 "cm"]
               [500 "d"]
               [400 "cd"]
               [100 "c"]
               [90 "xc"]
               [50 "l"]
               [40 "xl"]
               [10 "x"]
               [9 "ix"]
               [5 "v"]
               [4 "iv"]
               [1 "i"]]]
    (loop [n number
           res ""]
      (if (zero? n)
        res
        (let [[value symbol] (first (drop-while #(> (first %) n) roman))]
          (recur (- n value) (str res symbol)))))))