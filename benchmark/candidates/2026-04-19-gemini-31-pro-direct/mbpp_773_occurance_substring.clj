(defn occurance_substring [text pattern]
  (when (and (seq text) (seq pattern))
    (loop [idx 0
           res []]
      (if-let [found (clojure.string/index-of text pattern idx)]
        (recur (inc found) (conj res [pattern found]))
        (when (seq res)
          res)))))