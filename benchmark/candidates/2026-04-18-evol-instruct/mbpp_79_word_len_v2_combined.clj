(defn count-odd-length-words
  "Return the number of odd-length words in a collection of words.

  Ignore nil values and empty strings.

  Examples:
  (count-odd-length-words [\"cat\" \"\" nil \"dogs\" \"go\"]) ;=> 2
  (count-odd-length-words []) ;=> 0
  (count-odd-length-words nil) ;=> 0"
  [words]
  (count (filter #(and % (not (empty? %)) (odd? (count %))) (or words []))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (count-odd-length-words ["cat" "" nil "dogs" "go"]))))

(run-test test-variation)
