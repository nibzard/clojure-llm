(defn is_samepatterns
  "Write a function to check whether it follows the sequence given in the patterns vector."
  [colors patterns]
  (let [shape (fn [coll]
                (let [m (zipmap (distinct coll) (range))]
                  (map m coll)))]
    (= (shape colors) (shape patterns))))