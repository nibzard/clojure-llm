(defn count-odd-length-words
  "Return the number of words in the given collection whose length is odd.

Examples:
  (count-odd-length-words [\"cat\" \"no\" \"hello\" \"a\"]) => 3
  (count-odd-length-words []) => 0
  (count-odd-length-words nil) => 0"
  [words]
  (count (filter #(odd? (count %)) (or words []))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (count-odd-length-words ["cat" "no" "hello" "a"])))
  (is (= 0 (count-odd-length-words [])))
  (is (= 0 (count-odd-length-words nil))))

(run-test test-variation)
