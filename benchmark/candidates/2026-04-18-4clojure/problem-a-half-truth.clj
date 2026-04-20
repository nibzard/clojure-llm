(defn problem-a-half-truth
  [& bs]
  (and (some true? bs)
       (not-every? true? bs)))