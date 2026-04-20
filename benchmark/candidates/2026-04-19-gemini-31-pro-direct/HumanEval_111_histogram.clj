(defn histogram
  "Given a string representing a space separated lowercase letters, return a map
  of the letter with the most repetition and containing the corresponding count.
  If several letters have the same occurrence, return all of them."
  [test]
  (let [tokens (re-seq #"\S+" test)]
    (if (empty? tokens)
      {}
      (let [freqs (frequencies tokens)
            max-val (apply max (vals freqs))]
        (into {} (filter (fn [[_ v]] (= v max-val)) freqs))))))