(defn histogram
  "Given a string representing a space separated lowercase letters, return a map
  of the letter with the most repetition and containing the corresponding count.
  If several letters have the same occurrence, return all of them."
  [test]
  (if (clojure.string/blank? test)
    {}
    (let [freqs (frequencies (clojure.string/split test #"\s+"))
          max-count (apply max (vals freqs))]
      (into {} (filter (fn [[_ v]] (= v max-count)) freqs)))))