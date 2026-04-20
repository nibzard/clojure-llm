(defn text_match_wordz
  "Return a lazy sequence of words from the input text that contain the letter \"z\" or \"Z\".

  Examples:
  (text_match_wordz \"zebra fizz buzz\") => (\"zebra\" \"fizz\" \"buzz\")
  (text_match_wordz nil) => ()"
  [text]
  (if (seq text)
    (filter #(re-find #"[zZ]" %) (clojure.string/split text #"\s+"))
    ()))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '("zebra" "fizz" "buzz") (text_match_wordz "zebra fizz buzz"))))

(run-test test-variation)
