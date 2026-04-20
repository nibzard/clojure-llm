(defn pattern?
  "Return true when `patterns` describes the run-lengths of `xs`.

  A run-length pattern is a vector of positive integers indicating how many
  times each distinct consecutive value should repeat.

  Examples:
  (pattern? [1 1 2 2 2 3] [2 3 1]) ;=> true
  (pattern? [:a :a :b :b] [2 2])    ;=> true
  (pattern? [] [])                 ;=> true
  (pattern? nil [])                ;=> true

  The function must work for any sequential input, including lazy sequences."
  [xs patterns]
  (let [xs (seq xs)
        patterns (seq patterns)]
    (loop [s xs, ps patterns, curr nil, cnt 0]
      (cond
        (and (nil? s) (nil? ps)) true
        (or (nil? s) (nil? ps)) false
        :else
        (let [x (first s)
              p (first ps)]
          (if (or (nil? curr) (= x curr))
            (if (< cnt p)
              (recur (next s) ps x (inc cnt))
              (recur s (next ps) nil 0))
            false))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (pattern? [1 1 2 2 2 3] [2 3 1])))

(run-test test-variation)
