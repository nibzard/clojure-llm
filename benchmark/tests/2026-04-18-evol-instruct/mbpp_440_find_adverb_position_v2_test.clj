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
