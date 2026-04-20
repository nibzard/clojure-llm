(defn is_samepatterns
  "	Write a function to check whether it follows the sequence given in the patterns vector."
  [colors patterns]
  (let [colors   (seq colors)
        patterns (seq patterns)]
    (cond
      (nil? patterns) true
      (nil? colors) false
      :else
      (loop [remaining-colors colors
             remaining-patterns patterns]
        (cond
          (nil? remaining-patterns) true
          (nil? remaining-colors) false
          :else
          (let [pat (first remaining-patterns)
                matched (drop-while #(= % pat) remaining-colors)]
            (if (= matched remaining-colors)
              false
              (recur matched (next remaining-patterns))))))))