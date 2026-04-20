(defn nth-adverb-position
  "Return the zero-based index of the nth adverb in a sequence of word maps.

  Each item in `words` is a map like {:word \"quickly\" :pos :adverb}.

  If there are fewer than n adverbs, return nil.

  Examples:
  (nth-adverb-position [{:word \"run\" :pos :verb}
                        {:word \"quickly\" :pos :adverb}
                        {:word \"very\" :pos :adverb}]
                       1)
  ;; => 1

  (nth-adverb-position [{:word \"run\" :pos :verb}
                        {:word \"quickly\" :pos :adverb}
                        {:word \"very\" :pos :adverb}]
                       2)
  ;; => 2"
  [words n]
  (->> words
       (keep-indexed (fn [idx m]
                       (when (= :adverb (:pos m))
                         idx)))
       (drop (dec n))
       first))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (nth-adverb-position [{:word "run" :pos :verb}
                                 {:word "quickly" :pos :adverb}
                                 {:word "very" :pos :adverb}] 1)))
  (is (= 2 (nth-adverb-position [{:word "run" :pos :verb}
                                 {:word "quickly" :pos :adverb}
                                 {:word "very" :pos :adverb}] 2)))
  (is (= nil (nth-adverb-position [{:word "run" :pos :verb}
                                   {:word "quickly" :pos :adverb}] 2))))

(run-test test-variation)
