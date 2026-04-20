(defn vec_to_mini_roman
  "Given a vector of positive integers, convert each number to its lowercase Roman numeral equivalent.
  Restrictions:
  - Each number is between 1 and 1000 inclusive.
  - Return a vector of strings in the same order.

  Examples:
  >>> (vec_to_mini_roman [19 152 426])
  [\"xix\" \"clii\" \"cdxxvi\"]
  >>> (vec_to_mini_roman [1 4 9 10])
  [\"i\" \"iv\" \"ix\" \"x\"]"
  [nums]
  (let [roman-values [[1000 "m"] [900 "cm"] [500 "d"] [400 "cd"]
                      [100 "c"] [90 "xc"] [50 "l"] [40 "xl"]
                      [10 "x"] [9 "ix"] [5 "v"] [4 "iv"] [1 "i"]]]
    (mapv (fn [n]
            (loop [n n, pairs roman-values, out ""]
              (if (empty? pairs)
                out
                (let [[v s] (first pairs)]
                  (if (>= n v)
                    (recur (- n v) pairs (str out s))
                    (recur n (rest pairs) out)))))
          ) nums)))