(defn is_samepatterns
  "	Write a function to check whether it follows the sequence given in the patterns vector."
  [colors patterns]
  (let [step (fn [xs]
               (map first (partition-by identity xs)))]
    (= (vec (step colors))
       (vec patterns))))