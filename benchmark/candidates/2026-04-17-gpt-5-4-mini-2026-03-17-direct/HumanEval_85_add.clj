(defn add
  "Given a non-empty list of integers lst. add the even elements that are at odd indices..
  Examples:
  >>> (add [4 2 6 7])
  2"
  [lst]
  (reduce-kv (fn [sum idx n]
               (if (and (odd? idx) (even? n))
                 (+ sum n)
                 sum))
             0
             lst))