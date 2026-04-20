(defn filter-even-length-words
  "Given a collection of strings, return a lazy sequence of the words whose length is even.

  The function must:
  - ignore nil values in the input
  - work with any sequential collection, not just vectors
  - preserve the original order
  - return a lazy sequence

  Examples:
  >>> (filter-even-length-words [\"a\" \"to\" nil \"four\" \"five\" \"six\"])
  (\"to\" \"four\" \"six\")
  >>> (filter-even-length-words '(\"one\" \"two\" \"three\" \"even\"))
  (\"even\")"
  [words]
  (filter (fn [w] (and w (even? (count w)))) words))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '("to" "four" "six")
         (filter-even-length-words ["a" "to" nil "four" "five" "six"]))))

(run-test test-variation)
