(defn int_to_mini_roman
  "Given a positive integer, obtain its roman numeral equivalent as a string,
  and return it in lowercase.
  Restrictions: 1 <= num <= 1000"
  [number]
  (let [numerals [[1000 "m"]
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
           pairs numerals
           result []]
      (if (zero? n)
        (apply str result)
        (let [[value roman] (first pairs)]
          (if (>= n value)
            (recur (- n value) pairs (conj result roman))
            (recur n (rest pairs) result)))))))