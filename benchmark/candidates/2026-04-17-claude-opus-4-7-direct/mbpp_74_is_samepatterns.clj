(defn is_samepatterns
  "Write a function to check whether it follows the sequence given in the patterns vector."
  [colors patterns]
  (let [len (count patterns)]
    (and (= len (count colors))
         (every? identity
                 (map (fn [c p] (or (nil? p) (= c p)))
                      colors
                      patterns)))))