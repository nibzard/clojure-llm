(defn is_samepatterns
  "	Write a function to check whether it follows the sequence given in the patterns vector."
  [colors patterns]
  (let [groups (map first (partition-by identity colors))]
    (= (vec groups) (vec patterns))))