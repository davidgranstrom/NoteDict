
+ SimpleNumber {
    asNote{|style=\traditional|
        ^NoteDict.numberToNote(this, style)
    }
}

+ SequenceableCollection {
    asNote{|style=\traditional| 
        ^this.collect{|x| x.asNote(style) }
    }
}
