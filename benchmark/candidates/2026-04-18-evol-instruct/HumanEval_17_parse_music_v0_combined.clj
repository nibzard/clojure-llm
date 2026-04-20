(defn parse_music
  "Given a string of ASCII drum hits, return a vector of durations in beats.
   Use this legend:
   'x'  - whole hit, lasts 4 beats
   'x|' - half hit, lasts 2 beats
   '.|' - quarter hit, lasts 1 beat

   Ignore extra whitespace, and return an empty vector for nil or blank input.

   Examples:
   (parse_music \"x x| .| x|\")
   => [4 2 1 2]

   (parse_music nil)
   => []"
  [music-string]
  (let [tokens (when (and music-string (not (clojure.string/blank? music-string)))
                 (clojure.string/split music-string #"\s+"))]
    (vec
     (map {"x" 4, "x|" 2, ".|" 1} tokens))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [4 2 1 2] (parse_music "x x| .| x|")))
  (is (= [] (parse_music nil))))

(run-test test-variation)
