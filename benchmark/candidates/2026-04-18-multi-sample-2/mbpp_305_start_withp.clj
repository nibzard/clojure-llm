(defn start_withp
  "	Write a function to return two words from a list of words starting with letter 'p'."
  [words]
  (->> words
       (filter #(and (string? %) (.startsWith % "p")))
       (take 2)))