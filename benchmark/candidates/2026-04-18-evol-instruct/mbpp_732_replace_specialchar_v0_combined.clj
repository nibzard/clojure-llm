(defn normalize-punctuation
  "Return a lazy sequence of characters where every space, comma, or period in `chars`
   is replaced by a colon.

   Examples:
   (normalize-punctuation [\\a \\  \\b \\, \\c \\.])
   ;=> (\\a \\: \\b \\: \\c \\:)
   
   Works with any seqable input, including strings and infinite sequences."
  [chars]
  (map #(case %
           (\space \, \.) \:
           %) chars))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [\a \: \b \: \c \:] (vec (normalize-punctuation [\a \space \b \, \c \.]))))
  (is (= [\h \e \l \l \o \: \w \o \r \l \d] (vec (normalize-punctuation "hello world"))))
  (is (= [\a \: \a \: \a]
         (take 5 (normalize-punctuation (cycle [\a \space]))))))

(run-test test-variation)
