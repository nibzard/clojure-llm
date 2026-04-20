(defn exchange
  [lst1 lst2]
  (let [odd-in-lst1 (count (filter odd? lst1))
        even-in-lst2 (count (filter even? lst2))]
    (if (<= odd-in-lst1 even-in-lst2)
      "YES"
      "NO")))