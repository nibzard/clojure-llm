(defn add
  "Given a non-empty list of integers lst. add the even elements that are at odd indices..
  Examples:
  >>> (add [4 2 6 7])
  2"
  [lst]
  (->> lst
       (map-indexed vector)
       (filter (fn [[i v]] (and (odd? i) (even? v))))
       (map second)
       (reduce + 0)))