(defn histogram
  "Given a string representing a space separated lowercase letters, return a map
  of the letter with the most repetition and containing the corresponding count.
  If several letters have the same occurrence, return all of them.
  Example:
  >>> (histogram \"a b c\")
  {\"a\" 1 \"b\" 1 \"c\" 1}
  >>> (histogram \"a b b a\")
  {\"a\" 2 \"b\" 2}
  >>> (histogram \"a b c a b\")
  {\"a\" 2 \"b\" 2}
  >>> (histogram \"b b b b a\")
  {\"b\" 4}
  >>> (histogram \"\")
  {}"
  [test]
  (let [tokens (->> (clojure.string/split (or test "") #"\s+")
                    (remove clojure.string/blank?))
        freqs  (frequencies tokens)]
    (if (empty? freqs)
      {}
      (let [max-count (apply max (vals freqs))]
        (into {}
              (filter (fn [[_ v]] (= v max-count)) freqs))))))