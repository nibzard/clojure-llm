(defn start_withp
  "Write a function to return two words from a list of words starting with letter 'p'."
  [words]
  (->> words
       (filter #(and (string? %) (clojure.string/starts-with? % "p")))
       (take 2)
       vec))