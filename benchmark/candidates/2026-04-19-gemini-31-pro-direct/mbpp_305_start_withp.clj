(defn start_withp
  "Write a function to return two words from a list of words starting with letter 'p'."
  [words]
  (take 2 (filter #(re-find #"^[pP]" (str %)) words)))