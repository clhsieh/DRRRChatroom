#!/usr/bin/env python

import sys
import Image
import ImageQt
from PyQt4 import QtCore, QtGui
from HeadGen import *

class AvatarDialog(QtGui.QDialog):
    def __init__(self, parent=None):
        QtGui.QDialog.__init__(self, parent)
        #set size and title
        self.setGeometry(0, 0, 400, 300)
        self.setWindowTitle('Choose avatar')
        #set background color
        #palette = QtGui.QPalette()
        #palette.setColor(QtGui.QPalette.Background,QtCore.Qt.black)
        #self.setPalette(palette)
        #create initial avatar
        self.avatar = QtGui.QLabel()
        self.avatar.setPixmap(QtGui.QPixmap.fromImage(HeadGen('1.1.145.192.237', 1)))
        #create type button
        self.useGravatar = QtGui.QRadioButton('Use gravatar')
        self.useDRRR = QtGui.QRadioButton('Use Durarara style avatar')
        #create Gravarta text area
        self.email = QtGui.QLineEdit()
        self.email.setText('abc@abc.com')
        #create type group
        self.typeGroup = QtGui.QButtonGroup()
        self.typeGroup.setExclusive(True)
        self.typeGroup.addButton(self.useGravatar)
        self.typeGroup.addButton(self.useDRRR)
        #set layout
        avatarLayout = QtGui.QHBoxLayout()
        avatarLayout.addWidget(self.avatar)
        avatarLayout.setAlignment(QtCore.Qt.AlignCenter)
        emailLayout = QtGui.QHBoxLayout()
        emailLayout.addSpacing(25)
        emailLayout.addWidget(self.email)

        layout = QtGui.QVBoxLayout()
        layout.addLayout(avatarLayout)
        layout.addWidget(self.useGravatar)
        layout.addLayout(emailLayout)
        layout.addWidget(self.useDRRR)

        self.useDRRR.setChecked(True)

        self.setLayout(layout)
        

if __name__ == '__main__':
    app = QtGui.QApplication(sys.argv)
    avatar = AvatarDialog()
    avatar.show()
    sys.exit(app.exec_())
