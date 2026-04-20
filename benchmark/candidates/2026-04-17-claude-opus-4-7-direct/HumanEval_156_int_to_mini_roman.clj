(defn int_to_mini_roman
  "Given a positive integer, obtain its roman numeral equivalent as a string,
  and return it in lowercase.
  Restrictions: 1 <= num <= 1000"
  [number]
  (let [roman-map [[1000 "M"] [900 "CM"] [500 "D"] [400 "CD"]
                   [100 "C"] [90 "XC"] [50 "L"] [40 "XL"]
                   [10 "X"] [9 "IX"] [5 "V"] [4 "IV"] [1 "I"]]]
    (loop [n number
           result ""]
      (if (zero? n)
        result
        (let [[[value numeral] & rest] roman-map]
          (if (>= n value)
            (recur (- n value) (str result numeral))
            (recur n result)))))))