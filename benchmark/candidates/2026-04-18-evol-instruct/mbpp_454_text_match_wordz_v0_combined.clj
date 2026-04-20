(defn text_match_wordz
  "Return all words in a collection that contain the letter \"z\".

  Examples:
  (text_match_wordz [\"zebra\" \"apple\" \"pizza\" \"buzz\" \"cat\"])
  => (\"zebra\" \"pizza\" \"buzz\")

  Works with nil by returning an empty sequence."
  [text]
  (filter #(re-find #"z" %) (or text [])))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["zebra" "pizza" "buzz"]
         (text_match_wordz ["zebra" "apple" "pizza" "buzz" "cat"]))))

(run-test test-variation)
