(defn select_words
  "Given a sequence of words, return a vector of all words whose length is exactly n.
  Words may be strings or nil. Ignore nil values.
  If the input sequence is empty, return an empty vector.
  Preserve the original order of the words.

  Examples:
  >>> (select_words [\"cat\" nil \"horse\" \"bee\" \"\"] 3)
  [\"cat\" \"bee\" \"\"]
  >>> (select_words [] 2)
  []
  >>> (select_words [nil \"one\" \"two\" \"three\"] 3)
  [\"one\" \"two\"]"
  [words n]
  (vec (filter #(and % (= (count %) n)) words)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["cat" "bee" ""] (select_words ["cat" nil "horse" "bee" ""] 3)))
  (is (= [] (select_words [] 2)))
  (is (= ["one" "two"] (select_words [nil "one" "two" "three"] 3))))

(run-test test-variation)
