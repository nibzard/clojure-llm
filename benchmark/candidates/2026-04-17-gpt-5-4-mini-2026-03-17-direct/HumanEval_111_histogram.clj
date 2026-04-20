(defn histogram
  [test]
  (let [freqs (frequencies (remove clojure.string/blank? (clojure.string/split (or test "") #"\s+")))]
    (if (empty? freqs)
      {}
      (let [max-count (apply max (vals freqs))]
        (into {} (filter (fn [[_ v]] (= v max-count)) freqs))))))