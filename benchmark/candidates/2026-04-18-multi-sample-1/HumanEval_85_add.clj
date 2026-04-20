(defn add
  "Given a non-empty list of integers lst. add the even elements that are at odd indices..
  Examples:
  >>> (add [4 2 6 7])
  2"
  [lst]
  (->> lst
       (map-indexed vector)
       (filter (fn [[idx x]] (and (odd? idx) (even? x))))
       (map second)
       (reduce + 0)))