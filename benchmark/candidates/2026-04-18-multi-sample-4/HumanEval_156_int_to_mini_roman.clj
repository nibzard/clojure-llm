(defn int_to_mini_roman
  "Given a positive integer, obtain its roman numeral equivalent as a string,
  and return it in lowercase.
  Restrictions: 1 <= num <= 1000
  Examples:
  >>> (int_to_mini_roman 19)
  \"xix\"
  >>> (int_to_mini_roman 152)
  \"clii\"
  >>> (int_to_mini_roman 426)
  \"cdxxvi\""
  [number]
  (let [roman-pairs [[1000 "m"]
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
           pairs roman-pairs
           result []]
      (if (zero? n)
        (apply str result)
        (let [[value numeral] (first pairs)]
          (if (>= n value)
            (recur (- n value) pairs (conj result numeral))
            (recur n (rest pairs) result)))))))