(defn is_sublist
  "Write a function to check whether a list contains the given sublist or not."
  [l s]
  (cond
    (empty? s) true
    (empty? l) false
    :else (or (and (= (first l) (first s))
                   (= (take (count s) l) s))
              (is_sublist (rest l) s))))