(defn is_samepatterns
  "	Write a function to check whether it follows the sequence given in the patterns vector."
  [colors patterns]
  (let [colors   (or colors [])
        patterns (or patterns [])]
    (and (= (count colors) (count patterns))
         (= (count (set colors)) (count (set patterns)))
         (= (mapv patterns colors)
            patterns))))