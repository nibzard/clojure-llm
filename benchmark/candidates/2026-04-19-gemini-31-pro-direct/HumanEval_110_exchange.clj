(defn exchange
  [lst1 lst2]
  (if (<= (count (filter odd? lst1))
          (count (filter even? lst2)))
    "YES"
    "NO"))