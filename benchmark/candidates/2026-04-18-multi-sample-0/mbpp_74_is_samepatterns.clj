(defn is_samepatterns
  "	Write a function to check whether it follows the sequence given in the patterns vector."
  [colors patterns]
  (let [cseq (seq colors)
        pseq (seq patterns)]
    (cond
      (and (nil? cseq) (nil? pseq)) true
      (or (nil? cseq) (nil? pseq)) false
      :else
      (let [groups (map first (partition-by identity colors))]
        (= (vec groups) (vec patterns))))))