(defn parse_music
  "Input is a string containing a sequence of note tokens separated by arbitrary whitespace.
  Return a vector of beat lengths for each token.

  Token legend:
  \"w\"  - whole note, lasts 4 beats
  \"h\"  - half note, lasts 2 beats
  \"q\"  - quarter note, lasts 1 beat

  Ignore extra spaces, tabs, and newlines.

  Examples:
  (parse_music \"w h q\")
  => [4 2 1]

  (parse_music \"  q\\n\\th   w   q \")
  => [1 2 4 1]"
  [music-string]
  (->> (clojure.string/split (or music-string "") #"\s+")
       (remove clojure.string/blank?)
       (map {"w" 4 "h" 2 "q" 1})
       vec))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [4 2 1] (parse_music "w h q")))
  (is (= [1 2 4 1] (parse_music "  q\n\th   w   q ")))
  (is (= [] (parse_music "")))
  (is (= [] (parse_music nil))))

(run-test test-variation)
