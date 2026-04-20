(defn most-frequent-prefix
  "Return the longest prefix of a string that appears most frequently in the string's suffixes.
  If the input is nil or empty, return nil.

  Examples:
  (most-frequent-prefix \"banana\") ;=> \"a\"
  (most-frequent-prefix \"aaaa\")   ;=> \"a\"
  (most-frequent-prefix nil)       ;=> nil"
  [s]
  (when (seq s)
    (->> (range 1 (inc (count s)))
         (map #(subs s 0 %))
         (map (fn [p]
                [p (count (re-seq (re-pattern (java.util.regex.Pattern/quote p)) s))]))
         (apply max-key second)
         first)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "a" (most-frequent-prefix "banana")))
  (is (= "a" (most-frequent-prefix "aaaa")))
  (is (= nil (most-frequent-prefix nil))))

(run-test test-variation)
