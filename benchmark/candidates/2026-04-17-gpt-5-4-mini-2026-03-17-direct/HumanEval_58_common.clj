(defn common
  "Return sorted unique common elements for two lists."
  [l1 l2]
  (->> (clojure.set/intersection (set l1) (set l2))
       sort
       vec))